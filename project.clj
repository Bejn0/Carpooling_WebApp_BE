(defproject carpooling "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clojurewerkz/scrypt "1.2.0"]
                 [compojure "1.6.2"]
                 [org.postgresql/postgresql "42.5.2"]
                 [org.clojure/data.json "2.4.0"]
                 [funcool/struct "1.4.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [ring/ring-defaults "0.3.4"]
                 [ring/ring-core "1.9.6"]
                 [ring/ring-json "0.5.1"]
                 [ring-cors "0.1.13"]
                 [clojure.joda-time "0.7.0"]
                 [ring/ring-jetty-adapter "1.9.6"]]
  :main ^:skip-aot carpooling.core
  :profiles {:uberjar {:aot :all}})
