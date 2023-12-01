package controller;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUpEmptyStorage(){
        Storage storage = new ListStorage();
        Controller.setStorage(storage);
    }
}