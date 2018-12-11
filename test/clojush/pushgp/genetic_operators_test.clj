(ns clojush.pushgp.genetic-operators-test
    (:use midje.sweet)
    (:require [clojush.pushgp.genetic-operators :as go]))

(fact (+ 2 2) => even?)

;;; append-to-key Tests
(fact
    (go/append-to-key :key "-diversifying?") => "key-diversifying?")

;;; key-to-fn Tests
(defn myFunc-diversifying?
    []
    true)

(fact
    ((go/key-to-fn :myFunc "-diversifying?")) => true
      (provided (resolve (symbol "myFunc-diversifying?")) => myFunc-diversifying?))

;;; diversifying? Tests
(defn alwaysTruthy
    [ind argmap]
    (assoc ind :diversifying true))

(defn alwaysFalsy
    [ind argmap]
    (assoc ind :diversifying false))

(def argmap {:autoconstructive-diversification-test [:fn1 :fn2 :fn3]})

(fact
    (go/diversifying? {} argmap) => {:diversifying true}
      (provided
        (go/run-diversification-test :fn1 {:diversifying true} argmap) => {:diversifying true}
        (go/run-diversification-test :fn2 {:diversifying true} argmap) => {:diversifying true}
        (go/run-diversification-test :fn3 {:diversifying true} argmap) => {:diversifying true}))

(fact
    (go/diversifying? {} argmap) => {:diversifying false}
      (provided
        (go/run-diversification-test :fn1 {:diversifying true} argmap) => {:diversifying true}
        (go/run-diversification-test :fn2 {:diversifying true} argmap) => {:diversifying false}))
