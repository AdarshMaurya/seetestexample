package com.example.seecucumber.core;

public interface Application {

	public void init(boolean clearData);

	public void handleElementIdentificationException();

	public void install(String appLocation);

}