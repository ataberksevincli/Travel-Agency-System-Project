package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomAddView extends Layout {
    private JPanel container;
    private JComboBox cmb_otel;
    private JTextField fld_bedcapacity;
    private JComboBox cmb_pension;
    private JComboBox cmb_season;
    private JComboBox cmb_roomtype;
    private JTextField fld_m2;
    private JTextField fld_stock;
    private JTextField fld_adultprice;
    private JTextField fld_childprice;
    private JRadioButton btn_tv;
    private JRadioButton btn_minibar;
    private JRadioButton btn_consol;
    private JRadioButton btn_case;
    private JRadioButton btn_projection;
    private JButton btn_save;
    private JLabel lbl_hotel;
    private JLabel lbl_bedcapacity;
    private JLabel lbl_pension;
    private JLabel lbl_season;
    private JLabel lbl_roomtype;
    private JLabel lbl_m2;
    private JLabel lb_stock;
    private JLabel lbl_adultprice;
    private JLabel lbl_chilprice;

    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private Pension pension;
    private Object[] col_pension;
    private User user;
    private Hotel hotel;

    private Room room;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private Season season;

    public RoomAddView() {
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.pensionManager = new PensionManager();
        this.room = new Room();
        this.pension = new Pension();
        this.season = new Season();
        this.user = user;
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        guiInitilaze(700, 700);

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_otel.addItem(hotel.getComboItem());
        }

        this.cmb_otel.addActionListener(e -> {
            //Session comboBox'ı güncellenir.
            ArrayList<Season> seasons = this.seasonManager.getSeasonsByOtelId(((ComboItem) cmb_otel.getSelectedItem()).getKey());
            cmb_season.removeAllItems();

            for (Season season : seasons) {
                cmb_season.addItem(season.getComboItem());
            }

            //Pension comboBox'ı güncellenir.
            ComboItem selectedOtelItem = (ComboItem) cmb_otel.getSelectedItem();
            int selectedOtelId = selectedOtelItem.getKey();

            ArrayList<Pension> pensions = pensionManager.getPensionsByOtelId(selectedOtelId);
            cmb_pension.removeAllItems(); // Var olan öğeleri temizle

            // Yeni pansiyonları combobox'a ekle

            for (Pension pension : pensions) {
                cmb_pension.addItem(pension.getComboItem());
            }
        });

        for (Room room : this.roomManager.findAll()) {
            this.cmb_roomtype.addItem(room.getType());
        }


        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_stock, this.fld_adultprice, this.fld_childprice, this.fld_bedcapacity, this.fld_m2})) {
                Helper.showMsg("fill");
            } else {
                boolean result = false;

                ComboItem selectedHotel = (ComboItem) cmb_otel.getSelectedItem();
                this.room.setHotel_id(selectedHotel.getKey());
                ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();

                // PENSION VE SEASON SETLEYEMEMİŞSİN KONTROL ET AKŞAM GELİNCE


                this.room.setPension_id(selectedPension.getKey());
                this.room.setType((String) this.cmb_roomtype.getSelectedItem());
                this.room.setSeason_id(this.cmb_season.getSelectedIndex());
                this.room.setStock(Integer.parseInt(fld_stock.getText()));
                this.room.setAdult_price(Integer.parseInt(fld_adultprice.getText()));
                this.room.setChild_price(Double.parseDouble(fld_childprice.getText()));
                this.room.setBed_capacity(Integer.parseInt(fld_bedcapacity.getText()));
                this.room.setSquare_meter(Integer.parseInt(fld_m2.getText()));
                this.room.setTelevision(btn_tv.isSelected());
                this.room.setMinibar(btn_minibar.isSelected());
                this.room.setGame_console(btn_consol.isSelected());
                this.room.setSafe_box(btn_case.isSelected());
                this.room.setProjection(btn_projection.isSelected());
                result = this.roomManager.save(this.room);
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
