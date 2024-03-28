import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SeatReservationServer extends UnicastRemoteObject implements SeatReservationService {
	private List<Seat> seats;

	public SeatReservationServer() throws RemoteException {
		super();
		seats = new ArrayList<>();
		for (int i = 1; i <= 25; i++) {
			seats.add(new Seat(i));
		}
	}

	@Override
	public List<Seat> getAvailableSeats() throws RemoteException {
		List<Seat> availableSeats = new ArrayList<>();
		for (Seat seat : seats) {
			if (seat.isAvailable()) {
				availableSeats.add(seat);
			}
		}
		return availableSeats;
	}

	@Override
	public boolean reserveSeat(int seatNumber, String customerName) throws RemoteException {
		if (seatNumber < 1 || seatNumber > 25) {
			return false; // Invalid seat number
		}

		Seat seat = seats.get(seatNumber - 1);
		if (seat.isAvailable()) {
			seat.reserveSeat(customerName);
			System.out.printf("Seat %d - Reserved (%s)", seat.getSeatNumber(), seat.getCustomerName());
			return true;
		}
		return false; // Seat is already reserved
	}

	public static void main(String[] args) {
		try {
			SeatReservationServer server = new SeatReservationServer();
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			java.rmi.Naming.rebind("SeatReservationService", server);
			System.out.println("SeatReservationServer is running...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
