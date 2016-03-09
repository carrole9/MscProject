package com.ILoveLAMP.dao.jpa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ILoveLAMP.dao.UserDAO;
import com.ILoveLAMP.entities.User;

@Stateless
@Local
public class JPAUserDAO implements UserDAO {

	public static String filePath = "/home/user1/conygreJEE/solutions/JavaEE6Workspace/story3/src/main/webapp/Users.xls";
	private Collection<User> usersFromExcel;

	@PersistenceContext
	private EntityManager em;

	public void addUser(User user) {
		System.out.println("IN userDAO" + user.getId() + user.getUsername()
				+ user.getPassword() + user.getUsertype());

		Query query = em.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (!users.contains(user))
			em.merge(user);
	}

	@SuppressWarnings("unused")
	private void closeEntityManager(EntityManager em) {
		em.close();
	}

	public Collection<User> getUserByType(String usertype) {

		Query query = em
				.createQuery("from User user where user.usertype = :usertype");
		query.setParameter("usertype", usertype);

		@SuppressWarnings("unchecked")
		List<User> result = query.getResultList();
		return result;
	}

	public Collection<User> getAllUsers() {

		Query query = em.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		return users;
	}

	public void deleteUser(int id) {
		User user = em.find(User.class, 7);
		em.remove(user);
	}

	public User getUserIdByName(String username) {
		Query query = em
				.createQuery(" from User user where user.username = :username");

		query.setParameter("username", username);
		User result = (User) query.getSingleResult();
		return result;
	}

	public User getUserIdByPassword(String password) {
		Query query = em
				.createQuery(" from User user where user.password = :password");

		query.setParameter("password", password);
		User result = (User) query.getSingleResult();
		return result;
	}

	public User getUserById(int id) {

		Query query = em.createQuery("from User user where user.id = :id");
		query.setParameter("id", id);

		User result = (User) query.getSingleResult();
		return result;
	}

	@Override
	public void loadUsersFromExcel() {

		@SuppressWarnings("unused")
		Collection<User> usersExists = getAllUsers();

		try {
			// Create input stream for EXCEL
			HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(
					filePath));
			// the default indexing of 1st sheet in Excel is "0"
			// The syntax is：HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFSheet sheet = wookbook.getSheet("Sheet1");
			// getting the number of rows in the Excel file
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println(rows + "rows");
			// scan through all the rows
			for (int i = 0; i < rows; i++) {
				// read the top left unit cell
				HSSFRow row = sheet.getRow(i);
				// if the row is not empty
				if (row != null) {
					// getting all the columns in the Excel file
					int cells = row.getPhysicalNumberOfCells();
					System.out.println(cells + "columns");
					String value = "";
					// scan through all the columns
					for (int j = 0; j < cells; j++) {
						// get the data of the cell
						HSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC:
								value += cell.getNumericCellValue() + ",";
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value += cell.getStringCellValue() + ",";
								break;
							// default:
							// value += "0";
							// break;
							}

						}
					}

					String[] val = value.split(",");
					System.out.println(val.length);
					for (String string : val) {
						System.out.println(string);
					}

					double doubleId = Double.parseDouble(val[0]);
					int intId = (int) doubleId;
					System.out.println(intId);
					// // load data into mysql database
					User user = new User();
					// //now conveting the data
					user.setId(0);
					user.setUsername(val[1]);
					user.setPassword(val[2]);
					user.setUsertype(val[3]);

					System.out.println(user.getId() + user.getPassword()
							+ user.getUsername() + user.getUsertype());
					usersFromExcel.add(user);
					addUser(user);

				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
