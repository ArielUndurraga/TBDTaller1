package cl.citiaps.spring.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;

@RestController  
@RequestMapping("/actors")
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private FilmRepository filmRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Actor> getAllUsers() {
		return actorRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Actor findOne(@PathVariable("id") Integer id) {
		return actorRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
	@ResponseBody
	public List<Film> getActorFilms(@PathVariable("id") Integer id){
		return actorRepository.findOne(id).getFilms();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Actor create(@RequestBody Actor resource) {
	     return actorRepository.save(resource);
	}
	
	@RequestMapping(value = "/{id}/films/{id_film}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Actor update(@PathVariable("id") Integer id, @PathVariable("id_film") Integer id_film){
		Actor actor = actorRepository.findOne(id);
		Film film = filmRepository.findOne(id_film);
		if (actor != null && film != null){
			film.getActor().add(actor);
			filmRepository.save(film);
			return actor;
			
		}
		else{
			return null;
		}
	}
	 
}