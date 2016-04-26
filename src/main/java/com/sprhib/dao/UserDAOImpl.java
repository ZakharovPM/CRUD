package com.sprhib.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sprhib.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addUser(User user) {
		getCurrentSession().save(user);
	}

	public void updateUser(User user) {
		User userToUpdate = getUser(user.getId());
		userToUpdate.setName(user.getName());
		userToUpdate.setAge(user.getAge());
		userToUpdate.setIsAdmin(user.getIsAdmin());
		getCurrentSession().update(userToUpdate);

	}

	public User getUser(int id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	public void deleteUser(int id) {
		User user = getUser(id);
		if (user != null)
			getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return getCurrentSession().createQuery("from User").list();
	}

	public List<User> searchUsers(User user) {
		List<User> list = getUsers();
		List<User> resultList = new ArrayList<>();


		if (user.getId()!=null) {
			for (User us : list) {
				if (us.getId().equals(user.getId())) {
					resultList.add(us);
				}
			}
			if (user.getName() != null) {
				Iterator<User> it = resultList.iterator();
				while (it.hasNext()) {
					User taken = it.next();
					if (!taken.getName().contains(user.getName())) {
						it.remove();
					}
				}
			}
			if (user.getAge() != null) {
				Iterator<User> it = resultList.iterator();
				while (it.hasNext()) {
					User taken = it.next();
					if (!taken.getAge().equals(user.getAge())) {
						it.remove();
					}
				}

			}
			if (user.getIsAdmin()!=null){
				Iterator<User> it = resultList.iterator();
				while (it.hasNext()) {
					User taken = it.next();
					if (!taken.getIsAdmin().equals(user.getIsAdmin())) {
						it.remove();
					}
				}
			}
		}
		else if (user.getName()!=null){
			for(User us : list) {
				if (us.getName().contains(user.getName())) {
					resultList.add(us);
				}
			}
			if (user.getAge() != null) {
				Iterator<User> it = resultList.iterator();
				while (it.hasNext()) {
					User taken = it.next();
					if (!taken.getAge().equals(user.getAge())) {
						it.remove();
					}
				}

			}
			if (user.getIsAdmin()!=null){
				Iterator<User> it = resultList.iterator();
				while (it.hasNext()) {
					User taken = it.next();
					if (!taken.getIsAdmin().equals(user.getIsAdmin())) {
						it.remove();
					}
				}
			}
		}
		else if (user.getAge()!=null){
			for(User us : list) {
				if (us.getAge().equals(user.getAge())) {
					resultList.add(us);
				}
			}
			if (user.getIsAdmin()!=null){
				Iterator<User> it = resultList.iterator();
				while (it.hasNext()) {
					User taken = it.next();
					if (!taken.getIsAdmin().equals(user.getIsAdmin())) {
						it.remove();
					}
				}
			}

		}
		else if (user.getIsAdmin()!=null){
			for(User us : list) {
				if (us.getIsAdmin().equals(user.getIsAdmin())) {
					resultList.add(us);
				}
			}


		}
		if(user.getId()==null && user.getName()==null && user.getAge()==null && user.getIsAdmin()==null) {
			resultList.clear();
		}
		return resultList;
	}

}
