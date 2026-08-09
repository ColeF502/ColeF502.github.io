const mongoose = require('mongoose');

// Stores the customer information connected to reservations
const customerSchema = new mongoose.Schema({

  name: { type: String, required: true, trim: true },
  email: {
    type: String,
    required: true,
    trim: true,
    lowercase: true,
    unique: true,
    index: true,
    match: [/^\S+@\S+\.\S+$/, 'A valid email address is required']
  },

  phone: {
    type: String,
    required: true,
    trim: true,
    match: [/^\d{10}$/, 'The phone number must contain 10 digits']
  }
}, { timestamps: true });
const Customer = mongoose.model('customers', customerSchema);
module.exports = Customer;