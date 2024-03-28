import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class SeatReservationClient {
	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			SeatReservationService seatReservationService = (SeatReservationService) registry
					.lookup("SeatReservationService");

			while (true) {
				System.out.println("Current Available Seats:");
				List<Seat> availableSeats = seatReservationService.getAvailableSeats();
				displaySeats(availableSeats);

				System.out.print("Enter the seat number you want to reserve (0 to exit): ");
				int seatNumber = scanner.nextInt();

				if (seatNumber == 0) {
					System.out.println("Exiting client.");
					break;
				}

				if (seatNumber < 1 || seatNumber > 25) {
					System.out.println("Invalid seat number. Please choose a seat between 1 and 25.");
					continue;
				}

				System.out.print("Enter customer name: ");
				scanner.nextLine(); // Consume the newline character
				String customerName = scanner.nextLine();

				boolean reservationStatus = seatReservationService.reserveSeat(seatNumber, customerName);
				if (reservationStatus) {
					System.out.println("Seat " + seatNumber + " reserved for " + customerName);
				} else {
					System.out.println("Seat " + seatNumber + " is no longer available. Please choose another seat.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void displaySeats(List<Seat> seats) {
		for (Seat seat : seats) {
			System.out.println("Seat " + seat.getSeatNumber() + " - Available: " + seat.isAvailable());
		}
	}
}
