package view;

import business.*;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JButton btn_logout;
    private JTabbedPane pnl_menu;
    private JLabel lbl_welcome;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JScrollPane scrl_pension;
    private JScrollPane scrl_room;
    private JButton btn_add;
    private JPanel pnl_button;
    private JTextField fld_hotelname;
    private JTextField fld_city;
    private JTextField fld_adult;
    private JTextField fld_child;
    private JButton btn_searchhotel;
    private JButton btn_reset;
    private JButton btn_addroom;
    private JTable tbl_room;
    private JPanel pnl_hotelname;
    private JLabel lbl_hotelname;
    private JLabel lbl_city;
    private JLabel lbl_checkin;
    private JLabel lbl_checkout;
    private JLabel lbl_adult;
    private JLabel lbl_child;
    private JPanel pnl_reservation;
    private JPanel pnl_room;
    private JPanel pnl_room_filter;
    private JFormattedTextField fld_checkin;
    private JFormattedTextField fld_checkout;
    private Hotel hotel;
    private User user;
    private Object[] col_pension;
    private Object[] col_season;
    private Object[] col_hotel;
    private Object[] col_hotel_pension;
    private Object[] col_room;

    private HotelManager hotelManager;
    private UserManager userManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private final DefaultTableModel tmbdl_pension = new DefaultTableModel();

    private final DefaultTableModel tmbdl_season = new DefaultTableModel();

    private final DefaultTableModel tmbdl_room = new DefaultTableModel();

    private final DefaultTableModel tmbdl_hotel = new DefaultTableModel();

    public EmployeeView(User user) {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(1200, 700);
        this.user = user;
        this.lbl_welcome.setText("Welcome : " + this.user.getUsername());

        logOut();

        if (user == null) {
            dispose();
        }

        // Hotel
        loadHotelTable();
        loadHotelComponent();


        // Pension
        loadPensionTable();
        addHotelButton();

        //Season
        loadSeasonTable();

        //Room
        loadRoomTable();
        addRoomButton();

    }

    // EMPOLEEVIEW OTEL ADD SCREEN BUTTON
    private void addHotelButton() {
        this.btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelAddViewScreen();
            }
        });
    }

    private void logOut() {
        this.btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }

    // EMPOLEEVIEW OTEL ADD SCREEN VIEW
    private void HotelAddViewScreen() {
        HotelAddView hotelAddView = new HotelAddView();
        hotelAddView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadHotelTable();
            }
        });
        hotelAddView.setVisible(true);
    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Name", "Adress", "Mail", "Phone Number", "Hotel Star", "Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "Room Service"};
        ArrayList<Object[]> hotelList = HotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        this.createTable(this.tmbdl_hotel, this.tbl_hotel, col_hotel, hotelList);

    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotel);
        JPopupMenu hotel_menu = new JPopupMenu();
        hotel_menu.add("Add Pension Type").addActionListener(e -> {
            PensionTypeAddView pensionTypeAddView = new PensionTypeAddView();
            pensionTypeAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                }
            });
        });

        hotel_menu.add("Add Season").addActionListener(e -> {
            SeasonAddView seasonAddView = new SeasonAddView();
            seasonAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

    }

    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        this.createTable(this.tmbdl_pension, this.tbl_pension, col_pension, pensionList);
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date", "Finish Date"};
        ArrayList<Object[]> seasonList = seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        this.createTable(this.tmbdl_season, this.tbl_season, col_season, seasonList);
    }

    private void addRoomButton() {
        btn_addroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomAddViewScreen();
            }
        });
    }

    private void RoomAddViewScreen() {
        RoomAddView roomAddView = new RoomAddView();
        roomAddView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadRoomTable();
            }
        });
        roomAddView.setVisible(true);
    }

    public void loadRoomTable() {
        Object[] col_room = {"ID", "Hotel Name", "Pension","Season","Room Type", "Stok", "Adult Price", "Child Price", "Bed Capacity", "m2", "TV", "Minibar", "Console", "Cash Box", "Projection"};
        ArrayList<Object[]> roomList = roomManager.getForTable(col_room.length, this.roomManager.findAll());
        createTable(this.tmbdl_room, this.tbl_room, col_room, roomList);

    }


    private void createUIComponents() throws ParseException {
        this.fld_checkin = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkin.setText("13/01/2021");
        this.fld_checkout = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkout.setText("16/05/2021");
    }

}