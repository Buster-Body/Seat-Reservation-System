import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SeatReservationService extends Remote {
	List<Seat> getAvailableSeats() throws RemoteException;

	boolean reserveSeat(int seatNumber, String customerName) throws RemoteException;
}
