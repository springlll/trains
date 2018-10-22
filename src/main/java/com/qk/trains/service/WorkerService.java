package com.qk.trains.service;

import com.qk.trains.entity.Worker;
import com.qk.trains.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: trains
 * @description: 作业员服务
 * @author: Xiaotian
 * @create: 2018-09-21 14:59
 **/
@Service
public class WorkerService {

	@Autowired
	

	WorkerMapper workerMapper;

	@Cacheable(cacheNames = "workers")
	public    List<Worker> getAllDevice() {
		return workerMapper.listAll();
		
	}

	@CacheEvict(cacheNames = "workers")
	public void addWorker(int station_id,  String card_id, String remarks) {
		Worker worker = new Worker();
		worker.setStation_id(station_id);
		//worker.setName(name);
		worker.setCard_id(card_id);
		worker.setRemarks(remarks);
		workerMapper.add(worker);
	}

	@CacheEvict(cacheNames = "workers")
	public void deleteWorker(int id) {
		workerMapper.delete(id);
	}

	@CacheEvict(cacheNames = "devices")
	public void updateWorker(int id, int station_id, String card_id, String remarks) {
		Worker worker = new Worker();
		worker.setId(id);
		worker.setStation_id(station_id);
		//worker.setName(name);
		worker.setCard_id(card_id);
		worker.setRemarks(remarks);
		workerMapper.update(worker);
	}


}
