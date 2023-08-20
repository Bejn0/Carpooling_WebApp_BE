(ns carpooling.models.ride
  (:require [clojure.java.jdbc :as sql]
            [carpooling.db :refer :all]))

(defn get-rides []
  (sql/query db
             ["SELECT * FROM ride"]))