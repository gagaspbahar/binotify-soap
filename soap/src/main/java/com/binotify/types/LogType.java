package com.binotify.types;

import java.sql.Time;

public abstract class LogType {
  public String description;
  public String endpoint;
  public Time requested_at;
  public String ip;
}
