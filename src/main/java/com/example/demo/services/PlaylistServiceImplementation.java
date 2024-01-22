package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepository;
@Service
public class PlaylistServiceImplementation  implements PlaylistService {

	
	@Autowired
	PlaylistRepository repo;
	
	@Override
	public void addPlaylist(Playlist playlist) {
		repo.save(playlist);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	

}
