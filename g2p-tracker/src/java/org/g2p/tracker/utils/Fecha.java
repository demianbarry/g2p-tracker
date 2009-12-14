/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.utils;

import java.util.Date;

/**
 *
 * @author kristian
 */
public class Fecha extends Date{
    public static final int DIAS = 86400000;
    public static final int HORAS = 3600000;
    public static final int MINUTOS = 60000;
    public static final int SEGUNDOS = 1000;
    public static final int MILISEGUNDOS = 1;

    public Fecha(){
        super();
    }

    public Fecha(long time){
        super(time);
    }

    public Fecha(String cadena){
        super(cadena);
    }

    public Fecha(int year,int month,int date){
        super(year, month, date);
    }

    public Fecha(int year,int month,int date,int hrs,int min){
        super(year, month, date, hrs, min);
    }

    public Fecha(int year,int month,int date,int hrs,int min,int sec){
        super(year, month, date, hrs, min, sec);
    }

    /**
     * Averigua la diferencia que hay entre dos fechas
     * @param otraFecha
     * @param unidad unidad de medida (dias, horas, minutos, etc)
     * @return la diferencia en base a la unidad de medida especificada
     */
    public long getDiff(Date otraFecha, int unidad){
        if(otraFecha != null) {
            long diferencia = this.getTime() - otraFecha.getTime();
            return (long) (diferencia / unidad);
        } else {
            return 0;
        }

    }

}
