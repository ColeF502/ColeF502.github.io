const mongoose = require('mongoose');
const Trip = require('../models/travlr');
const Model = mongoose.model('trips');

const tripsList = async (req, res) => {
    const q = await Model
        .find({})
        .exec();

    if (!q) {
        return res
            .status(404)
            .json({ message: 'Trips not found' });
    } else {
        return res
            .status(200)
            .json(q);
    }
};

const tripsFindByCode = async (req, res) => {
    const q = await Model
        .find({ 'code': req.params.tripCode })
        .exec();

    if (!q) {
        return res
            .status(404)
            .json({ message: 'Trip not found' });
    } else {
        return res
            .status(200)
            .json(q);
    }
};

// Adds a new Trip
const tripsAddTrip = async (req, res) => {
    const newTrip = new Trip({
        code: req.body.code,
        name: req.body.name,
        length: req.body.length,
        start: req.body.start,
        resort: req.body.resort,
        perPerson: req.body.perPerson,
        image: req.body.image,
        description: req.body.description
    });

    const q = await newTrip.save();

    if (!q) {
        return res
            .status(400)
            .json({ message: 'Trip could not be added' });
    } else {
        return res
            .status(201)
            .json(q);
    }

   
    
};

// Updates a Trip
const tripsUpdateTrip = async (req, res) => {
    const q = await Model
        .findOneAndUpdate(
            { 'code': req.params.tripCode },

            {
                code: req.body.code,
                name: req.body.name,
                length: req.body.length,
                start: req.body.start,
                resort: req.body.resort,
                perPerson: req.body.perPerson,
                image: req.body.image,
                description: req.body.description
            }
        )
        .exec();

    if (!q) {
        return res
            .status(404)
            .json({ message: 'Trip not found' });
    } else {
        return res
            .status(200)
            .json(q);
    }
};
// Deletes a Trip
const tripsDeleteTrip = async (req, res) => {
    const q = await Model
        .findOneAndDelete({ 'code': req.params.tripCode })
        .exec();

    if (!q) {
        return res
            .status(404)
            .json({ message: 'Trip not found' });
    } else {
        return res
            .status(200)
            .json(q);
    }
};

module.exports = {
    tripsList,
    tripsFindByCode,
    tripsAddTrip,
    tripsUpdateTrip,
    tripsDeleteTrip

};