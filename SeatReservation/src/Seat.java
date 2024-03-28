import java.io.Serializable;

public class Seat implements Serializable {
	private int seatNumber;
	private String customerName;

	public Seat(int seatNumber) {
		this.seatNumber = seatNumber;
		this.customerName = null;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void reserveSeat(String customerName) {
		this.customerName = customerName;
	}

	public void cancelReservation() {
		this.customerName = null;
	}

	public boolean isAvailable() {
		return customerName == null;
	}
}
