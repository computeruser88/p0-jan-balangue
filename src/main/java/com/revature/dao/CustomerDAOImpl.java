package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.Customer;
import com.revature.util.ConnectionUtil;
import org.apache.log4j.*;

public class CustomerDAOImpl implements CustomerDAO {

	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Customer> getCustomers() {
		
		List<Customer> customerList = new ArrayList<>();
		
		String sql = "SELECT * FROM CUSTOMER";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				Customer c = new Customer();
				
				customerList.add(c);
			}
		} catch (IOException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		return customerList;
	}

	@Override
	public Customer getCustomerById(int id) {
		
		return null;
	}

	@Override
	public int createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
