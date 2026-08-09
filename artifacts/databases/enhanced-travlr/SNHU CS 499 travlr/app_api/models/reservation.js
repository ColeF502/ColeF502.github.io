const mongoose = require('mongoose');

// Stores the booking details and connects them to the right trip and customer
const reservationSchema = new mongoose.Schema({
  trip: { type: mongoose.Schema.Types.ObjectId, ref: 'trips', required: true, index: true },
  customer: { type: mongoose.Schema.Types.ObjectId, ref: 'customers', required: true, index: true },
  travelStartDate: { type: Date, required: true },
  travelEndDate: {
    type: Date,
    required: true,
    validate: {
      validator: function (value) {
        return !this.travelStartDate || value > this.travelStartDate;
      },
      message: 'The travel end date must be after the start date'


    }
  },
  travelers: { type: Number, required: true, min: 1 },
  totalPrice: { type: Number, required: true, min: 0 },
  status: {
    type: String,
    enum: ['pending', 'confirmed', 'cancelled'],
    default: 'pending'

  }
}, { timestamps: true });

// Prevents the same customer from booking the same trip and start date more than once
reservationSchema.index(
  { trip: 1, customer: 1, travelStartDate: 1 },
  { unique: true }

);

const Reservation = mongoose.model('reservations', reservationSchema);
module.exports = Reservation;