(ns carpooling.routes.rides
  (:require [clojure.data.json :as json]
            [clojure.string :as str]
            [compojure.core :refer :all]
            [ring.util.response :as response]
            [carpooling.models.ride :as db]
            [joda-time]))

(defn cast-string-to-timestamp-without-time-zone [string]
  (->> string
       (str/trim)
       (joda-time.convert/to-sql-timestamp)))

(defn create-ride [start-location end-location date space user-id]
          (db/create-ride
            {:start_location start-location
             :end_location end-location
             :dateTime (cast-string-to-timestamp-without-time-zone (str date))
             :space (Integer/parseInt space)
             :user_id (Integer/parseInt user-id)})
          (response/status 200))

(defroutes rides-routes
           (GET "/rides" []
             (json/write-str
               (response/response (db/get-rides)))
             )
           (GET "/rides/:id" request
             (let [id (:id (:route-params request))]
             (json/write-str
               (response/response (db/get-users-rides id)))
             ))
           (POST "/rides" request
             (json/write-str
               (let [{:keys [start_location end_location date space user_id]}
                     (json/read-str (slurp(:body request)) :key-fn keyword)]
                              (create-ride start_location end_location date space user_id)
                              ))
             )
           (DELETE "/rides/:id" request
             (let [id (:id (:route-params request))]
               (json/write-str
                 (response/response (db/delete-ride id)))
               )
             ))
