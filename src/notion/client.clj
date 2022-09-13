(ns notion.client
  (:require [notion.config :refer [load-default-config]]))

(defprotocol ClientProtocol
  (token [this]))

(deftype Client [config]
  ClientProtocol
  (token [_] (:token config)))

(defn init!
  "Loads the client configuration and returns a client protocol.

   config-path (optional) is a string containing the path of the
   configuration file. It must be an '.edn' in order to work."
  [& config-path]
  (let [config (load-default-config)]
    (->Client config)))
