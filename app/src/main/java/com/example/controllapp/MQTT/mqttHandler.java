package com.example.controllapp.MQTT;


import android.content.Context;

import com.example.controllapp.DB.User;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class mqttHandler {

    private MqttClient client;
    public String USERNAME;
    public String PASSWORD;

    public void connect(String brokerUrl, String clientId) {
        try {
            // persistencia de datos
            MemoryPersistence persistence = new MemoryPersistence();

            client = new MqttClient(brokerUrl, clientId, persistence);
            MqttConnectOptions connectOptions = new MqttConnectOptions();


            // Entrega de datos
            USERNAME = "androidteststiqq";
            PASSWORD = "W0U2XNxCKinXaOBv";

            connectOptions.setUserName(USERNAME);
            connectOptions.setPassword(PASSWORD.toCharArray());

            // Opciones de conexion
            connectOptions.setCleanSession(true);

            // Conectar al broker
            client.connect(connectOptions);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message, Context context) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            client.publish(topic, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        try {
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
