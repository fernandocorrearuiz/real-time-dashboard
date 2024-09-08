import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import './App.css';

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    // Connecting to WebSocket
    const ws = new SockJS('http://localhost:8080/ws');
    const stompClient = Stomp.over(ws);

    stompClient.connect({}, frame => {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/traffic', trafficData => {
        console.log('Received: ', trafficData);
        try {
            const newMessage = JSON.parse(trafficData.body);
            console.log('Parsed Message:', newMessage);
            setData(previousData => [...previousData, newMessage]);
        } catch (error) {
            console.error('Error parsing JSON:', error);
        }
      });
    });

    return () => {
      if (stompClient) {
        stompClient.disconnect();
        console.log('Disconnected');
      }
    };
  }, []);


//  useEffect(() => {
//    // Setup WebSocket connection here
//    const ws = new WebSocket('ws://localhost:8080/traffic'); // Adjust this URL to your WebSocket endpoint
//    ws.onmessage = event => {
//      const message = JSON.parse(event.data);
//      setData(previousData => [...previousData, message]);
//    };
//    return () => ws.close();
//  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>Real-Time Traffic Dashboard</h1>
      </header>
      <div className="data-container">
        {data.map((item, index) => (
          <div key={index} className="data-item">
            {item.message} {/* Adjust depending on the structure of your data */}
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
