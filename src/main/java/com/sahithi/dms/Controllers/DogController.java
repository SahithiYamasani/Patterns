package com.sahithi.dms.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sahithi.dms.Models.Dog;
import com.sahithi.dms.Models.Trainer;
import com.sahithi.dms.repository.DogRepository;
import com.sahithi.dms.repository.TrainerRespository;

@Controller
public class DogController 
{
	ModelAndView mv = new ModelAndView();
	
	@Autowired
	DogRepository dogrepo;
	@Autowired
	TrainerRespository trainerRepo;
//	@RequestMapping("dogHome")
//	public String home()
//	{
//		return "home";
//	}
	
	
	@RequestMapping("dogHome")
	public ModelAndView home()
	{
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("add")
	public ModelAndView add()
	{
		mv.setViewName("addNewDog.html");
		Iterable<Trainer> trainerList = trainerRepo.findAll();
		mv.addObject("trainers", trainerList);
		return mv;
	}
	
	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog, @RequestParam("trainerId") int id)
	{
		Trainer trainer = trainerRepo.findById(id).orElse(new Trainer());
		dog.setTrainer(trainer);
		dogrepo.save(dog);
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs()
	{
		mv.setViewName("viewDogs");
		Iterable<Dog> dogsLits = dogrepo.findAll();
		mv.addObject("dogs",dogsLits);
		return mv;
	}
	
	@RequestMapping("editDog")
	public ModelAndView editDog(Dog dog)
	{
		dogrepo.save(dog);
		mv.setViewName("editDog");
		return mv;
	}
	
	@RequestMapping("deleteDog")
	public ModelAndView deleteDog(Dog dog)
	{
		/*
		 * Optional<Dog> dogFound = dogrepo.findById(dog.getId());
		 * if(dogFound.isPresent()) { dogrepo.delete(dog); }
		 */
		//return home();
		
		/*
		 * List<Dog> dogsFound = dogrepo.findByName(dog.getName()); for(Dog d :
		 * dogsFound) { dogrepo.delete(d); } return home();
		 */
		Dog d =	dogrepo.findById(dog.getId()).orElse(new Dog());
		dogrepo.delete(d);
		
		return home();
	}
		
		@RequestMapping("search")
		public ModelAndView searchByID(int id)
		{
			 Dog dogFound = dogrepo.findById(id).orElse(new Dog());
			 mv.addObject(dogFound);
			 mv.setViewName("searchResults");
			 return mv;
			
		}
		
		@RequestMapping("addTrainer")
		public ModelAndView addTrainer()
		{
			mv.setViewName("addNewTrainer");
			return mv;
		}
		
		@RequestMapping("trainerAdded")
		public ModelAndView addNewTrainer(Trainer trainer)
		{
			trainerRepo.save(trainer);
			mv.setViewName("home");
			return mv;
		}
	

}
