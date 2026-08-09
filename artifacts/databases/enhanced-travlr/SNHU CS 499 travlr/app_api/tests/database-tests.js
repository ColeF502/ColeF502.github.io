const assert = require('assert');
const mongoose = require('mongoose');
const Trip = require('../models/travlr');
const Customer = require('../models/customer');
const Reservation = require('../models/reservation');

const testDatabase = async () => {
    try {
        
        // Uses a separate database so the real Travlr records aren't affected
        await mongoose.connect('mongodb://127.0.0.1:27017/travlr_test');
        await Trip.deleteMany({});
        await Customer.deleteMany({});
        await Reservation.deleteMany({});
        // Ensures that the unique indexes are ready before testing duplicates
        await Trip.syncIndexes();
        await Customer.syncIndexes();
        await Reservation.syncIndexes();

        const trip = await Trip.create({
            code: 'TEST100',
            name: 'Test Reef',
            length: '4 nights / 5 days',
            start: new Date('2026-12-01'),
            resort: 'Test Resort',
            perPerson: 500,
            capacity: 5,
            remainingAvailability: 5,
            image: 'test.jpg',
            description: 'Trip used for database testing'
        });

        const customer = await Customer.create({
            name: 'Test Customer',
            email: 'testcustomer@example.com',
            phone: '5551112222'

        });

        const reservation = await Reservation.create({
            trip: trip._id,
            customer: customer._id,
            travelStartDate: new Date('2026-12-01'),
            travelEndDate: new Date('2026-12-05'),
            travelers: 2,
            totalPrice: 1000,
            status: 'confirmed'
        });

        // Confirms that the reservation's connected to the right trip and customer
        assert.strictEqual(
            reservation.trip.toString(),
            trip._id.toString()
        );


        assert.strictEqual(
            reservation.customer.toString(),
            customer._id.toString()
        );

        console.log('Reservation connections test passed');

        // Confirms that an invalid end date is rejected
        let validationFailed = false;

        try {
            await Reservation.create({
                trip: trip._id,
                customer: customer._id,
                travelStartDate: new Date('2027-01-10'),
                travelEndDate: new Date('2027-01-05'),
                travelers: 1,
                totalPrice: 500,
                status: 'confirmed'
            });
        } catch (err) {
            validationFailed = true;
        }

        assert.strictEqual(validationFailed, true);
        console.log('Reservation validation test passed');

        // Reduces the remaining spaces when there's enough existing availability
        const updatedTrip = await Trip.findOneAndUpdate(
            {
                _id: trip._id,
                remainingAvailability: { $gte: 2 }
            },

            {
                $inc: { remainingAvailability: -2 }
            },

            {
                returnDocument: 'after'
            }

        );

        assert.strictEqual(updatedTrip.remainingAvailability, 3);
        console.log('Availability update test passed');

        // Prevents an update when the requested travelers will exceed availability
        const overbookedTrip = await Trip.findOneAndUpdate(
            {
                _id: trip._id,
                remainingAvailability: { $gte: 10 }
            },

            {
                $inc: { remainingAvailability: -10 }
            },

            {
                returnDocument: 'after'
            }


        );

        assert.strictEqual(overbookedTrip, null);
        console.log('Overbooking prevention test passed');

        // Confirms that the same reservation can't be added twice
        let duplicateFailed = false;

        try {
            await Reservation.create({
                trip: trip._id,
                customer: customer._id,
                travelStartDate: new Date('2026-12-01'),
                travelEndDate: new Date('2026-12-05'),
                travelers: 2,
                totalPrice: 1000,
                status: 'confirmed'
            });

        } catch (err) {
            duplicateFailed = err.code === 11000;

        }
        assert.strictEqual(duplicateFailed, true);
        console.log('Duplicate reservation test passed');
        console.log('All database tests passed');

    } catch (err) {
        console.error('Database tests failed:', err.message);
        process.exitCode = 1;

    } finally {
        await mongoose.connection.close();
    }

};


testDatabase();