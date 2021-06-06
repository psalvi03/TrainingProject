package com.nt.test;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
			Configuration cfg=null;
			SessionFactory factory=null;
			Session ses=null;
			Product prod=null;
			Transaction tx=null;
			boolean flag=false;
		   int idVal=0;
			//Activate hibernate frameWork
		cfg=new Configuration();
		//supply hbrnate cfg file as input file
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		//build session factory
		factory=cfg.buildSessionFactory();
		//open session
		ses=factory.openSession();
		// create entity object to save with db s/w
		prod=new Product();
		prod.setPid(108); prod.setPname("Chair"); //prod.setPrice(2500);prod.setQty(15);
		try {
			tx=ses.beginTransaction();  //internall call con.setAutoCommit(false) to begin the Tx
			//save object
			idVal=(int)ses.save(prod);
			System.out.println("idVal====  "+idVal);
			flag=true;
		}//try
		catch(HibernateException he)
		{
			he.printStackTrace();
			flag=false;
		}//catch
		finally {
			//commit or rollback tx
			if(flag=true) {
				tx.commit();  //internally call con.commit
			System.out.println("Object(Data) is saved");
			}
			else {
				tx.rollback();  //internally calls con.rollback
		  	System.out.println("Data is not saved");
		  	}
		}//finally
	}//main
}//class
