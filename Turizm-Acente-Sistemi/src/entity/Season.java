package entity;

import core.ComboItem;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Season {

    private int id;
    private int hotel_id;
    private LocalDate start_date;
    private LocalDate finish_date;

    public Season(int id, int hotel_id, LocalDate start_date, LocalDate finish_date) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public Season() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getId(), this.getStart_date() + " - " + this.getFinish_date());};
}
