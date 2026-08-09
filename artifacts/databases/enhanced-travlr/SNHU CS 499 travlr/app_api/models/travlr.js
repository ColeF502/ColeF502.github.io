const mongoose = require('mongoose');

// Defines the information and validation that's used for each trip
const tripSchema = new mongoose.Schema({
  code: { type: String, required: true, trim: true, uppercase: true, 
    unique: true, index: true },
  name: { type: String, required: true, trim: true, index: true },
  length: { type: String, required: true, trim: true },
  start: { type: Date, required: true },
  resort: { type: String, required: true, trim: true },
  perPerson: { type: Number, required: true, min: 0 },
  capacity: { type: Number, required: true, min: 1, default: 20 },
  remainingAvailability: {
    type: Number,
    required: true,
    min: 0,
    default: 20,
    validate: {
      validator: function (value) {
        return value <= this.capacity;
      },

      message: 'Remaining availability cannot be greater than the trip capacity'
    }
  },

  image: { type: String, required: true, trim: true },
  description: { type: String, required: true, trim: true }

}, { timestamps: true });

const Trip = mongoose.model('trips', tripSchema);
module.exports = Trip;