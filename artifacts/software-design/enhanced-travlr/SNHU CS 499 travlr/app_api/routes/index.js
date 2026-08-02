const express = require('express');
const router = express.Router();
const tripsController = require('../controllers/trips');
const authController = require('../controllers/authentication');
const jwt = require('jsonwebtoken'); // Enables JSON Web Tokens

// Checks for a valid JWT before allowing access to protected API routes
function authenticateJWT(req, res, next) {
    const authHeader = req.headers['authorization'];

    if (!authHeader) {
        console.log('Authorization header is missing.');
        return res.sendStatus(401);
    }

    const headers = authHeader.split(' ');

    // The authorization header should contain "Bearer" followed by the token.
    if (headers.length !== 2 || headers[0] !== 'Bearer') {
        console.log('Authorization header is not in the expected format.');
        return res.sendStatus(401);
    }
    const token = headers[1];

    jwt.verify(token, process.env.JWT_SECRET, (err, verified) => {
        if (err) {
            console.log('JWT verification failed.');
            return res.sendStatus(401);
        }

        // Stores the decoded token information so protected routes can use it if need be
        req.auth = verified;
        // Only continues to the protected route once the token's been verified
        next();

    });
}
router
    .route('/register')
    .post(authController.register);

router
    .route('/login')
    .post(authController.login);

router
    .route('/trips')
    .get(tripsController.tripsList)
    .post(authenticateJWT, tripsController.tripsAddTrip);

router
    .route('/trips/:tripCode')
    .get(tripsController.tripsFindByCode)
    .put(authenticateJWT, tripsController.tripsUpdateTrip)
    .delete(authenticateJWT, tripsController.tripsDeleteTrip);

module.exports = router;