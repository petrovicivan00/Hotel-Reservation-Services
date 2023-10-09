# Hotel Reservation System

**Description**

This is a hotel reservation system consisting of three services: User Service, Reservation Service, and Notification Service. It allows users to browse and book hotel accommodations, while hotel managers can manage hotel information. The Notification Service sends emails for successful bookings and reminders.

**Functionalities**

**User Service:**

- User Types: The system supports multiple user types with different data and privileges, including Admin, Client, and Hotel Manager.
- Registration: Clients and Hotel Managers can register, and activation emails are sent for confirmation.
- User Suspension: Admin can temporarily ban users from accessing the application.
- Login: Users can log in using their email and password to receive a JWT token.
- Profile Editing: Users can edit their profiles.
- User Ranking: Clients are ranked based on the number of reservations, and discounts are applied according to their rank.

**Reservation Service:**

- Hotel Data Management: Hotel managers can add and update hotel information, including room types, room prices, and room schedules.
- Listing Available Accommodations: Users can filter and sort available accommodations by city, hotel, and date.
- Booking: Clients can book available rooms, and the Notification Service is asynchronously notified.
- Reservation Cancellation: Both clients and hotel managers can cancel reservations.
- Review System: Clients can leave reviews, and the system provides filtering options.
- Top-Rated Hotels: List of hotels with the best average ratings.

**Notification Service:**

- Notification Types: Admin defines notification types with parameters.
- Activation Email: Sends activation emails.
- Password Reset Email.
- Booking Confirmation: Notifies clients and hotel managers.
- Reservation Cancellation Notification: Notifies clients and hotel managers.
- Reminder: Sends a reminder two days before a reservation.
- Notification Archive: Stores sent notifications, and admin can filter them.
