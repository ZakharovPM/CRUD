package com.sprhib.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.User;
import com.sprhib.service.UserService;

@Controller
public class UserController {
	List<User> searResult = new ArrayList<>();

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addUserPage(User user) {

		return "add-user-form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingUser(@ModelAttribute User user, BindingResult result) {

		FormValidator formValidator = new FormValidator();

		formValidator.validate(result.getModel().get("user"), result);
		if(result.hasErrors()){

			ModelAndView modelAndView = new ModelAndView("add-user-form",result.getModel());

			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("home");
		userService.addUser(user);
		
		String message = "Пользователь \"" + user.getName() + "\"  успешно добавлен в базу.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/list","/","/index"})
	public ModelAndView listOfUsers(@RequestParam(required = false) Integer page) {
		ModelAndView modelAndView = new ModelAndView("list-of-users");
		
		List<User> users = userService.getUsers();
		PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
		pagedListHolder.setPageSize(10);
		modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

		if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

		modelAndView.addObject("page", page);
		if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
			pagedListHolder.setPage(0);
			modelAndView.addObject("users", pagedListHolder.getPageList());
		}
		else if(page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page-1);
			modelAndView.addObject("users", pagedListHolder.getPageList());
		}

		return modelAndView;
	}
	@RequestMapping(value="result")
	public ModelAndView resultOfSearch(@RequestParam(required = false) Integer page) {
		ModelAndView modelAndView;
		if(!searResult.isEmpty()){
			modelAndView = new ModelAndView("result-of-search");
			PagedListHolder<User> pagedListHolder = new PagedListHolder<>(searResult);
			pagedListHolder.setPageSize(10);
			modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
			modelAndView.addObject("sizeresult", pagedListHolder.getNrOfElements());

			if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

			modelAndView.addObject("page", page);
			if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
				pagedListHolder.setPage(0);
				modelAndView.addObject("users", pagedListHolder.getPageList());
			}
			else if(page <= pagedListHolder.getPageCount()) {
				pagedListHolder.setPage(page-1);
				modelAndView.addObject("users", pagedListHolder.getPageList());
			}
		}
		else {

			modelAndView = new ModelAndView("search-users");
			modelAndView.addObject("searchUser", new User());
			modelAndView.addObject("isSearch", "Результаты поиска не найдены, заполните форму");
		}


		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-user-form");
		User user = userService.getUser(id);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingUser(@ModelAttribute User user, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		userService.updateUser(user);
		
		String message = "Пользователь \"" + user.getName() + "\"  успешно отредактирован.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id) {
		if(!searResult.isEmpty()){
			for(User us : searResult)
			{
				if(us.getId().equals(id)){
					searResult.remove(us);
					break;
				}
			}
		}
		ModelAndView modelAndView = new ModelAndView("home");
		String deletedName = userService.getUser(id).getName();
		userService.deleteUser(id);
		String message = "Пользователь \"" + deletedName + "\"  успешно удален.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value="/search-init", method=RequestMethod.GET)
	public ModelAndView searchUser() {
		ModelAndView modelAndView = new ModelAndView("search-users");

		modelAndView.addObject("searchUser", new User());
		return modelAndView;
	}
	@RequestMapping(value="/search-init", method = RequestMethod.POST)
	public ModelAndView searchUser(@ModelAttribute User user, @RequestParam(required = false) Integer page) {
		ModelAndView modelAndView = new ModelAndView("result-of-search");
		searResult = userService.searchUsers(user);

		PagedListHolder<User> pagedListHolder = new PagedListHolder<>(searResult);
		pagedListHolder.setPageSize(10);
		modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
		modelAndView.addObject("sizeresult", pagedListHolder.getNrOfElements());

		if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

		modelAndView.addObject("page", page);
		if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
			pagedListHolder.setPage(0);
			modelAndView.addObject("users", pagedListHolder.getPageList());
		}
		else if(page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page-1);
			modelAndView.addObject("users", pagedListHolder.getPageList());
		}

		return modelAndView;
	}

}
