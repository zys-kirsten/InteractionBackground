package com.interaction.dao;

import com.interaction.pojo.Responderdata;

public interface ResponderdataDAO {

	int addResponderdata(Responderdata responderdata);

	Responderdata findById(int ridi);

	void updateResponderdata(Responderdata responderdata);

}
