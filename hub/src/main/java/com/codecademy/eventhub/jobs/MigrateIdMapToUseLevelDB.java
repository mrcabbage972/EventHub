package com.codecademy.eventhub.jobs;

import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Map;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;

public class MigrateIdMapToUseLevelDB {
  public static void main(String[] args) throws IOException {
    String userStorageDirectory = args[0];

    String filename = userStorageDirectory + "/id_map.ser";
    File file = new File(filename);
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      Map<String, Integer> idMap = (Map<String, Integer>) ois.readObject();
      int currentId = ois.readInt();

      Options options = new Options();
      options.createIfMissing(true);
      try (DB idMapDb = JniDBFactory.factory.open(new File(userStorageDirectory + "/id_map.db"), options)) {
        for (Map.Entry<String, Integer> entry : idMap.entrySet()) {
          idMapDb.put(bytes(entry.getKey()), bytes("" + entry.getValue()));
        }
        idMapDb.put(bytes("__eventtracker__id"), bytes("" + currentId));
      }
    }
  }
}