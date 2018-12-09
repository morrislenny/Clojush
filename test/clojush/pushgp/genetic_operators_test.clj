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

(fact
    (go/diversifying? {} {:autoconstructive-diversification-test [:fn1 :fn2 :fn3]}) => {:diversifying true}
      (provided
        (go/key-to-fn :fn1 "-diversifying?") => alwaysTruthy
        (go/key-to-fn :fn2 "-diversifying?") => alwaysTruthy
        (go/key-to-fn :fn3 "-diversifying?") => alwaysTruthy))

(fact
    (go/diversifying? {} {:autoconstructive-diversification-test [:fn1 :fn2 :fn3]}) => {:diversifying false}
      (provided
        (go/key-to-fn :fn1 "-diversifying?") => alwaysTruthy
        (go/key-to-fn :fn2 "-diversifying?") => alwaysFalsy))
