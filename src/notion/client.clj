(ns notion.client
  (:require
    [notion.types.client :refer [->Client]]
    [notion.config :refer [load-default-config]]))

(defn init!
  "Loads the client configuration and returns a client protocol.

   config-path (optional) is a string containing the path of the
   configuration file. It must be an '.edn' in order to work."
  [& config-path]
  (let [config (load-default-config)]
    (->Client config)))
