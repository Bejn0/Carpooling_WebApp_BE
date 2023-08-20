(ns carpooling.models.reservation
  (:require [clojure.java.jdbc :as sql]
            [carpooling.db :refer :all]))

(defn create-reservation [reservation]
  (sql/insert! db :reservation reservation))