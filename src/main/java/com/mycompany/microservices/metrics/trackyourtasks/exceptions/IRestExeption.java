package com.mycompany.microservices.metrics.trackyourtasks.exceptions;

/**
 * Interface to define event log-enabled exception
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
public interface IRestExeption {

    int getCode();

    String getDescription();
}
