const Customer = require('../models/customer');
const Reservation = require('../models/reservation');
const Trip = require('../models/travlr');

// Gets all the reservations along with their trip and customer information
const reservationsList = async (req, res) => {
    try {
        const reservations = await Reservation
            .find({})
            .populate('trip')
            .populate('customer')
            .exec();

        return res
            .status(200)
            .json(reservations);

    } catch (err) {
        return res
            .status(500)
            .json({ message: 'Reservations could not be retrieved' });
    }
};
// Uses the reservation data to calculate booking statistics and estimated revenue
const reservationsStatistics = async (req, res) => {
    try {
        const statistics = await Reservation.aggregate([
            {
                $match: {
                    status: { $ne: 'cancelled' }
                }
            },
            {
                $group: {
                    _id: null,

                    totalBookings: { $sum: 1 },
                    totalTravelers: { $sum: '$travelers' },
                    estimatedRevenue: { $sum: '$totalPrice' }
                }
            },
            {
                $project: {
                    _id: 0,
                    totalBookings: 1,
                    totalTravelers: 1,
                    estimatedRevenue: 1
                }
            }


        ]);

        return res
            .status(200)
            .json(statistics[0] || {
                totalBookings: 0,
                totalTravelers: 0,
                estimatedRevenue: 0
            });

    } catch (err) {
        return res
            .status(500)
            .json({ message: 'Booking statistics could not be calculated' });
    }

};

// Adds a new reservation and then connects it to the right trip and customer
const reservationsAddReservation = async (req, res) => {
    let updatedTrip = null;
    let travelers = 0;

    try {
        travelers = Number(req.body.travelers);

        if (!Number.isInteger(travelers) || travelers < 1) {
            return res
                .status(400)
                .json({ message: 'The number of travelers must be at least one' });
        }

        const existingTrip = await Trip
            .findOne({ code: req.body.tripCode })
            .exec();

        if (!existingTrip) {
            return res
                .status(404)
                .json({ message: 'Trip not found' });

        }

        // Only reserves the spaces when the trip's got enough availability
        updatedTrip = await Trip
            .findOneAndUpdate(
                {
                    _id: existingTrip._id,
                    remainingAvailability: { $gte: travelers }
                },
                {
                    $inc: { remainingAvailability: -travelers }
                },
                {
                    returnDocument: 'after'
                }
            )
            .exec();

        if (!updatedTrip) {
            return res
                .status(400)
                .json({ message: 'The trip does not have enough remaining availability' });
        }

        // Uses the existing customer when the email's already in the database
        let customer = await Customer
            .findOne({ email: req.body.email })
            .exec();

        if (!customer) {
            customer = await Customer.create({
                name: req.body.name,
                email: req.body.email,
                phone: req.body.phone
            });
        }

        const reservation = await Reservation.create({
            trip: updatedTrip._id,
            customer: customer._id,
            travelStartDate: req.body.travelStartDate,
            travelEndDate: req.body.travelEndDate,
            travelers: travelers,
            totalPrice: req.body.totalPrice,
            status: req.body.status || 'pending'

        });

        return res
            .status(201)
            .json(reservation);

    } catch (err) {
        // Returns the spaces if the reservation couldn't be saved
        if (updatedTrip) {
            try {
                await Trip
                    .findByIdAndUpdate(
                        updatedTrip._id,
                        { $inc: { remainingAvailability: travelers } }
                    )


                    .exec();

            } catch (availabilityErr) {
                console.log('The trip availability could not be restored.');
            }
        }

        // Gives a clearer response when the reservation's already in the database
        if (err.code === 11000) {
            return res
                .status(409)
                .json({ message: 'This reservation already exists' });
        }

        return res
            .status(400)
            .json({
                message: 'Reservation could not be added',
                error: err.message
            });
    }

};

module.exports = {
    reservationsList,
    reservationsStatistics,
    reservationsAddReservation

};