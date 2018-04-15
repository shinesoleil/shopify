var assert = require('assert');
var request = require('request');
var chai = require('chai');
var expect = chai.expect;

describe('Array', function () {

    describe('#indexOf()', function () {
        it('should return -1 when the value is not present', function () {
            assert.equal([1, 2, 3].indexOf(4), -1);
        });
    });

    describe('#request', function () {
        it('should return 200 when get users', function (done) {
            request.get('http://app:8080/users', function (err, res, body) {
                expect(res.statusCode).to.equal(200);
                done();
            });
        });
    });

});