package com.interaction.service;

import com.interaction.pojo.Student;

public interface ResponderdataService {

	Integer startResponder(int seId);

	Student listDoneStudent(int rdid);

	void endResponder(Integer rdid);

	void resetResponder(int rdid);

	int stuBeginResponder(int sid, int rdid) throws InterruptedException;

}
