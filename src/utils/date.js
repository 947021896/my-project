export function formatDate (timeString) {
  let date = new Date(timeString)
  // 注意js里面的getMonth是从0开始的
  return date.getFullYear() + '-' +
    (date.getMonth() + 1) + '-' +
    date.getDate() + ' ' +
    date.getHours() + ':' +
    date.getMinutes() + ':' +
    date.getSeconds()
}
