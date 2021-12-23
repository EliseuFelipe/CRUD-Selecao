import axios from 'axios'

export const http = axios.create({
    baseURL: 'http://localhost:8080/',
    headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "http://localhost:8081",
        "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS"                  
      }
});