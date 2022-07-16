(ns notion.client
  (:require
    [notion.api :as api]
    [notion.config :refer [load-default-config]]
    [notion.user :as user]
    [notion.page :as page]
    [notion.block :as block]
    [notion.database :as database]
    [notion.types.client :refer :all]))

(defn init!
  "Loads the client configuration and returns a client protocol.

   config-path (optional) is a string containing the path of the
   configuration file. It must be an '.edn' in order to work."
  [& config-path]
  (let [config (load-default-config)]
    (->Client config)))

(defn search
  "Searches pages and databases (including childrens) titles that matches
   the given query.

   options is a map that can includes those keys:
    :sort - Sorts the result by the provided criteria. Only one at the time
            is currently allowed.
      :direction - Direction to sort. Must be :ascending or :descending
      :timestamp - Name of the timestamp to sort. Possible value is :last_edited_time

    :filter - Filters the results based on the provided criteria. filter must
              be a map that include :value and/or :property
      :value - Value of the property. Can be :database or :page. At the moment,
               only :object is available (see notion's doc)
      :property - Name of the property. Can be :database or :page. At the moment
                  only :object is available (see notion's doc)
    :start_cursor - Pagination starting point or the result.
    :page_size - Number or result per page. Maximum is 100.

    https://developers.notion.com/reference/post-search"
  ([client query options]
    (let [params (merge {:query query} options)]
      (api/get client "/search" params)))
  ([client query]
    (search client query {})))

(defn user
  "Return the user with the given id"
  [client id]
  (user/find client id))

(defn users
  "Returns all users"
  [client]
  (user/all client))

(defn database
  "Returns the database with the given id"
  [client id]
  (database/find id))

(defn block
  "Returns the block with the given id"
  [client id]
  (block/find client id))

(defn blocks
  "Returns all blocks"
  [client]
  (block/all client))

(defn page
  "Returns the page with the given id"
  [client id]
  (page/find client id))

(defn pages
  "Returns all pages"
  [client id]
  (page/all client))

(defn parent
  "Returns the parent of the given object"
  [client object]
  (let [id (:parent-id object),
        type (:parent-type object)
        func (type {:page page, :database database})]
    (func client id)))

(defn created-by
  "Returns the user who created the object"
  [client object]
  (user client (:created-by-id object)))

(defn last-edited-by
  "Returns the last user who edited the object"
  [client object]
  (user client (:last-edited-by-id object)))
