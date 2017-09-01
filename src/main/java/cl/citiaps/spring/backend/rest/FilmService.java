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
@RequestMapping("/films")
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private ActorRepository actorRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Film> getAllUsers() {
		return filmRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Film findOne(@PathVariable("id") Integer id) {
		return filmRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
	@ResponseBody
	public List<Actor> getFilmActors(@PathVariable("id") Integer id){
		return filmRepository.findOne(id).getActor();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Film create(@RequestBody Film resource) {
	     return filmRepository.save(resource);
	}
	
	@RequestMapping(value = "/{id}/actors/{id_actor}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Film update(@PathVariable("id") Integer id, @PathVariable("id_actor") Integer id_actor){
		Actor actor = actorRepository.findOne(id_actor);
		Film film = filmRepository.findOne(id);
		if (actor != null && film != null){
			film.getActor().add(actor);
			return filmRepository.save(film);
			
		}
		else{
			return null;
		}
	}
	 
}