# Retirement Auto-Savings API

A production-grade Spring Boot REST service that automates retirement savings through expense rounding, temporal investment rules, and long-term return projections. The system is designed for high performance and supports large-scale transaction processing using optimized prefix-sum and TreeMap-based algorithms.

## Key Features

* Automated remanent calculation via expense rounding
* Temporal rule engine supporting fixed and additive investment periods
* Efficient range aggregation using prefix sum 
* Inflation-adjusted return projections for NPS and Index Fund strategies
* Transaction validation and filtering with detailed error reporting
* Containerized deployment with public Docker image

## Public Docker Image

```bash
docker pull kaustubhpatil6729/hackerrank:latest
docker run -p 5477:5477 kaustubhpatil6729/hackerrank:latest
```

## API Endpoints

```
POST /blackrock/challenge/v1/transactions:parse
POST /blackrock/challenge/v1/transactions:validator
POST /blackrock/challenge/v1/transactions:filter
POST /blackrock/challenge/v1/returns:nps
POST /blackrock/challenge/v1/returns:index
GET  /blackrock/challenge/v1/performance
```

## Architecture Highlights

* Stateless, scalable REST design
* Optimized time-range queries using TreeMap prefix sums
* Clean separation across controller, service, and utility layers
* Fully containerized and ready for cloud deployment

## Author

Kaustubh Patil
