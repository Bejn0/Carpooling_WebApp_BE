(ns carpooling.core
  (:require [ring.adapter.jetty :as jetty]
            [carpooling.handler :refer [app]]))



(defonce server (atom nil))

(defn start-server []
  (reset! server (jetty/run-jetty (fn [req] (app req))  ;; a really basic handler
                                  {:port 3001     ;; listen on port 3001
                                   :join? false})))

(defn stop-server []
  (when-some [s @server]
    (.stop s)
    (reset! server nil)))