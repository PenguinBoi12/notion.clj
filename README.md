# notion.clj
This is a Notion API wrapper written in Clojure. The goal is to create an easy, clean and complete way to communicate with [Notion](notion.so) through
their [API](https://developers.notion.com/).

# Getting started
### Installation
- Install [lein](https://leiningen.org/) (optional)
- _Still in construction_

### Configuration
- For this part, follow [Notion's guide](https://developers.notion.com/docs/getting-started#getting-started).
- Once you have you your [integration](https://www.notion.so/my-integrations) setup, create a `resources` folder a config file named `config.edn` then add `{:token "You token here"}` into it.

# Example(s)
### Retrive a user
```clojure
(ns my-notion-project.core
  (:require notion.client :as client))

(defn -main [& args]
  (let [client (client/init!)
        user (client/user client "...")]
    (println user))))
```

# Contributing
Everyone is welcome to participate in the development of the library. You can contribute to the project by simply opening an issue, by improving some
current features or even by adding your own. If you want to contribute but don't have any idea on what to work on, you can checkout out
[issues](https://github.com/PenguinBoi12/notion.clj/issues) page.

# License
Distributed under the GNU General Public License v3.0
