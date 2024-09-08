# Real-Time Traffic Dashboard

This project is a Real-Time Traffic Dashboard that displays traffic data consumed from Apache Kafka, processed by a Spring Boot backend, and visualized through a React frontend.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java JDK 11 or newer
- Apache Kafka and Zookeeper
- Node.js and npm
- Git

## Installation

To install the Real-Time Traffic Dashboard, follow these steps on Windows:

```bash
# Clone the repository
git clone https://github.com/yourusername/real-time-dashboard.git
cd real-time-dashboard

# Start Zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# Start Kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties

# Create Kafka Topic
.\bin\windows\kafka-topics.bat --create --topic traffic-data --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1

# Build and Run the Spring Boot Application

# In a new command prompt, start the React Application
cd ..\frontend
npm install
npm start

# Open another command prompt to send messages to Kafka
cd ..
.\bin\windows\kafka-console-producer.bat --topic traffic-data --bootstrap-server localhost:9092
