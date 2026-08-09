const mongoose = require('mongoose');
const Trip = require('../models/travlr');
const Model = mongoose.model('trips');



const tripsList = async (req, res) => {
    try {
        const trips = await Model
            .find({})
            .exec();

        if (trips.length === 0) {
            return res
                .status(404)
                .json({ message: 'Trips not found' });
        }

        return res
            .status(200)
            .json(trips);

    } catch (err) {
        return res
            .status(500)
            .json({ message: 'Trips could not be retrieved' });
    }

};

const tripsFindByCode = async (req, res) => {
    try {
        const trip = await Model
            .findOne({ code: req.params.tripCode })
            .exec();

        if (!trip) {
            return res
                .status(404)
                .json({ message: 'Trip not found' });
        }
        return res
            .status(200)
            .json(trip);

    } catch (err) {
        return res
            .status(500)
            .json({ message: 'The trip could not be retrieved' });
    }

};

// Adds a new Trip
const tripsAddTrip = async (req, res) => {
    try {
        const newTrip = new Trip({
            code: req.body.code,
            name: req.body.name,
            length: req.body.length,
            start: req.body.start,
            resort: req.body.resort,
            perPerson: req.body.perPerson,
            capacity: req.body.capacity ?? 20,
            remainingAvailability: req.body.remainingAvailability ?? 20,
            image: req.body.image,
            description: req.body.description
        });
        const trip = await newTrip.save();

        return res
            .status(201)
            .json(trip);

    } catch (err) {
        if (err.code === 11000) {
            return res
                .status(409)
                .json({ message: 'A trip with this code already exists' });
        }

        return res
            .status(400)
            .json({
                message: 'Trip could not be added',
                error: err.message

            });
    }
};

// Updates a Trip
const tripsUpdateTrip = async (req, res) => {
    try {
        const updatedTrip = {
            code: req.body.code,
            name: req.body.name,
            length: req.body.length,
            start: req.body.start,
            resort: req.body.resort,
            perPerson: req.body.perPerson,
            image: req.body.image,
            description: req.body.description
        };

        // Only updates these values when they were included in the request
        if (req.body.capacity !== undefined) {
            updatedTrip.capacity = req.body.capacity;
        }
        if (req.body.remainingAvailability !== undefined) {
            updatedTrip.remainingAvailability = req.body.remainingAvailability;
        }

        const trip = await Model
            .findOneAndUpdate(
                { code: req.params.tripCode },
                updatedTrip,
                {
                    returnDocument: 'after',
                    runValidators: true
                }
            )
            .exec();


        if (!trip) {
            return res
                .status(404)
                .json({ message: 'Trip not found' });
        }

        return res
            .status(200)
            .json(trip);

    } catch (err) {
        if (err.code === 11000) {
            return res
                .status(409)
                .json({ message: 'A trip with this code already exists' });

        }

        return res
            .status(400)
            .json({
                message: 'Trip could not be updated',
                error: err.message

            });
    }
};
// Deletes a Trip
const tripsDeleteTrip = async (req, res) => {
    try {
        const trip = await Model
            .findOneAndDelete({ code: req.params.tripCode })

            .exec();

        if (!trip) {
            return res
                .status(404)
                .json({ message: 'Trip not found' });
        }

        return res
            .status(200)
            .json(trip);

    } catch (err) {
        return res
            .status(500)
            .json({ message: 'Trip could not be deleted' });
    }
};


module.exports = {
    tripsList,
    tripsFindByCode,
    tripsAddTrip,
    tripsUpdateTrip,
    tripsDeleteTrip

};