import { ACCESS_TOKEN, API_BASE_URL } from './constants/constants'

export const request = (options) => {
  const headers = new Headers({
    'Content-Type': 'application/json',
  })

  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append(
      'Authorization',
      'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
    )
  }

  const defaults = { headers: headers }
  options = Object.assign({}, defaults, options)

  return fetch(options.url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json)
      }
      return json
    })
  )
}

export function siema() {
  return request({
    url: API_BASE_URL + '/siema',
    method: 'GET',
  }).then((response) => {
    console.log(response)
  })
}

export function expenseGet() {
  const userId = localStorage.getItem('userId')

  return request({
    url: API_BASE_URL + userId + '/expenses',
    method: 'GET',
  })
}

export function expensePost(data) {
  return request({
    url: API_BASE_URL + '/expense',
    method: 'POST',
    body: JSON.stringify(data),
  })
}

export function signup(signupRequest) {
  return request({
    url: API_BASE_URL + '/auth/signup',
    method: 'POST',
    body: JSON.stringify(signupRequest),
  })
}

export function login(loginRequest) {
  return request({
    url: API_BASE_URL + '/auth/signin',
    method: 'POST',
    body: JSON.stringify(loginRequest),
  }).then((response) => {
    localStorage.setItem('accessToken', response.accessToken)
    localStorage.setItem('userId', response.userId)
  })
}
