package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public boolean save(Room room){
        if (room.getId() !=0){
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    public boolean update( Room room){
        if (this.getById(room.getId()) == null){

            Helper.showMsg("notFound");
        }
        return this.roomDao.update(room);
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Room> roomList) {
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for (Room room : roomList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getHotel_id();
            rowObject[i++] = room.getPension_id();
            rowObject[i++] = room.getSeason_id();
            rowObject[i++] = room.getType();
            rowObject[i++] = room.getStock();
            rowObject[i++] = room.getAdult_price();
            rowObject[i++] = room.getChild_price();
            rowObject[i++] = room.getBed_capacity();
            rowObject[i++] = room.getSquare_meter();
            rowObject[i++] = room.isTelevision();
            rowObject[i++] = room.isMinibar();
            rowObject[i++] = room.isGame_console();
            rowObject[i++] = room.isSafe_box();
            rowObject[i++] = room.isProjection();
            roomRowList.add(rowObject);
        }
        return roomRowList;
    }
    public  Room getById(int id){
        return this.roomDao.getById(id);
    }
}
