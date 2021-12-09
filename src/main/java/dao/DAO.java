
  package dao;
  
  import java.util.List;
  
  import org.hibernate.Session; 
  import org.hibernate.Transaction;
import org.hibernate.query.Query;

import context.HibernateUtil; 
  import entity.*; 
  
  public class DAO {
  
 /**
	 * Get Product By Id
	 * 
	 * @param id
	 * @return
	 */

  
	  public Product getProduct(int pid) {

	        Transaction transaction = null;
	        Product product = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // get an user object
	            product = session.get(Product.class, pid);
	            // commit transaction
	            transaction.commit();
	            
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return product;
	    }
  
	  public useraccount getUser(int id) {

	        Transaction transaction = null;
	        useraccount user = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // get an user object
	            user = session.get(useraccount.class, id);
		              
	            // commit transaction
	            transaction.commit();
	            
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return user;
	    }
  
  
 /**
	 * Get all products
	 * 
	 * @return
	 */
		  @SuppressWarnings("unchecked") 
		  public List<Product> listAllProduct() { 
			  try {
		  return
		  HibernateUtil.getSessionFactory().openSession().createQuery("From Product").
		  getResultList(); } catch (Exception e) { e.printStackTrace(); 
		  } return null;
		  }
		  
		  @SuppressWarnings("unchecked") 
		  public List<Category> listAllCategory() { 
			  try
		  { 
				  return
		  HibernateUtil.getSessionFactory().openSession().createQuery("From Category").
		  getResultList(); 
				  } catch (Exception e) { e.printStackTrace(); } 
			  return null;
		  } 
		  @SuppressWarnings("unchecked") 
		  public List<Product> listProductByCate(int cid) { 
			  try {
		  return
		  HibernateUtil.getSessionFactory().openSession().createQuery("From Product Where cateid LIKE '"+cid+"'" ).
		  getResultList(); } catch (Exception e) { e.printStackTrace(); 
		  } return null;
		  }
		  @SuppressWarnings("unchecked") 
		  public List<Product> listProductByName(String Name) { 
			  try {
		  return
		  HibernateUtil.getSessionFactory().openSession().createQuery("From Product Where pname LIKE '%"+Name+"%'" ).
		  getResultList(); } catch (Exception e) { e.printStackTrace(); 
		  } return null;
		  }
		  
		  
		  public void saveUser(useraccount user) {
			        Transaction transaction = null;
			        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			            // start a transaction
			            transaction = session.beginTransaction();
			            // save the  object
			            session.save(user);
			            // commit transaction
			           transaction.commit();
			           
			        } catch (Exception e) {
			            if (transaction != null) {
			                transaction.rollback();
			            }
			            e.printStackTrace();
			        }
			    }

		   public useraccount validate(String userName, String password) {

			        Transaction transaction = null;
			        useraccount user = null;
			        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			            // start a transaction
			            transaction = session.beginTransaction();
			            // get an user object
			            user = (useraccount) session.createQuery("FROM useraccount U WHERE U.uName = :userName").setParameter("userName", userName)
			                .uniqueResult();

			            if (user != null && user.getuPass().equals(password)) {
			                return user;
			            }
			            // commit transaction
			            transaction.commit();
			        } catch (Exception e) {
			            if (transaction != null) {
			                transaction.rollback();
			            }
			            e.printStackTrace();
			        }
			        return null;
			    }
		   public boolean accountcheck(String userName) {

		        Transaction transaction = null;
		        useraccount users = null;
		        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		            // start a transaction
		            transaction = session.beginTransaction();
		            // get an user object
		            users = (useraccount) session.createQuery("FROM useraccount U WHERE U.uName = :userName").setParameter("userName", userName)
		                .uniqueResult();

		            if (users != null && users.getuName().equals(userName)) {
		                return true;
		            }
		            // commit transaction
		            transaction.commit();
		        } catch (Exception e) {
		            if (transaction != null) {
		                transaction.rollback();
		            }
		            e.printStackTrace();
		        }
		        return false;
		    }
		   @SuppressWarnings("unchecked") 
			  public List<Product> listProductBySellId(int id) { 
				  try {
			  return
			  HibernateUtil.getSessionFactory().openSession().createQuery("From Product  Where sellerid LIKE '" +id+ "'").
			  getResultList(); } catch (Exception e) { e.printStackTrace(); 
			  } return null;
			  }
		   public void deleteProduct(int pid) {

		        Transaction transaction = null;
		        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		            
		            transaction = session.beginTransaction();
		            Query query = session.createQuery("delete Product where pid = :id");
		            query.setParameter("id", pid);
		            query.executeUpdate();
		            
		           transaction.commit();
		           
		        } catch (Exception e) {
		            if (transaction != null) {
		                transaction.rollback();
		            }
		            e.printStackTrace();
		        }
		    }
		   public void saveProduct(Product product) {
		        Transaction transaction = null;
		        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		            // start a transaction
		            transaction = session.beginTransaction();
		            // save the  object
		            session.save(product);
		            // commit transaction
		           transaction.commit();
		           
		        } catch (Exception e) {
		            if (transaction != null) {
		                transaction.rollback();
		            }
		            e.printStackTrace();
		        }
		    }
		   public void updateProduct(int pid, String name, String image, double price, String title, String Des, int cid) {

		        Transaction transaction = null;
		        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		            
		            transaction = session.beginTransaction();
		            Query query = session.createQuery("update Product set pname = : name , pimage = : image , pprice = : price , ptitle = : title , pdescription = : Des , cateid = : cid  where pid = :id");
		            query.setParameter("id", pid);
		            query.setParameter("name", name);
		            query.setParameter("image", image);
		            query.setParameter("price", price);
		            query.setParameter("title", title);
		            query.setParameter("Des", Des);
		            query.setParameter("cid", cid);
		            query.executeUpdate();
		            
		           transaction.commit();
		           
		        } catch (Exception e) {
		            if (transaction != null) {
		                transaction.rollback();
		            }
		            e.printStackTrace();
		        }
		    }
		 
		 
  }
		 